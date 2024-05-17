package com.example.everyreading.controller.auth

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EmailAuth {
    var db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()
    fun cadastrarUsuario(user: Map<String, String>) {
        val email = user["email"]
        val senha = user["senha"]
        val nome = user["username"]

        if (email != null && senha != null && nome != null) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Cadastro do usuário bem-sucedido
                        val firebaseUser = FirebaseAuth.getInstance().currentUser

                        // Atualizar o perfil do usuário com o nome
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nome)
                            .build()

                        firebaseUser?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { profileTask ->
                                if (profileTask.isSuccessful) {
                                    // Atualização do perfil bem-sucedida
                                    // Agora você pode adicionar as informações adicionais do usuário ao Firestore
                                    val userAdditionalInfo = mapOf(
                                        "username" to nome,
                                        // Adicione outros campos conforme necessário
                                    )

                                    // Adicionar informações adicionais do usuário ao Firestore
                                    db.collection("Users")
                                        .document(firebaseUser.uid)
                                        .set(userAdditionalInfo)
                                        .addOnSuccessListener {
                                            Log.d(ContentValues.TAG, "Informações adicionais do usuário adicionadas com sucesso ao Firestore")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(ContentValues.TAG, "Erro ao adicionar informações adicionais do usuário ao Firestore", e)
                                        }
                                } else {
                                    // Falha ao atualizar o perfil do usuário
                                    Log.w(ContentValues.TAG, "Falha ao atualizar o perfil do usuário")
                                }
                            }
                    } else {
                        // Falha no cadastro do usuário
                        Log.w(ContentValues.TAG, "Falha no cadastro do usuário", task.exception)
                    }
                }
        } else {
            // Dados do usuário incompletos, trate esse caso adequadamente
            Log.w(ContentValues.TAG, "Dados do usuário incompletos")
        }
    }
    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail|hotmail)\\.com$")
        return emailRegex.matches(email)
    }
}