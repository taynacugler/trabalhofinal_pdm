package com.example.trabfinaltayna.Bean

data class Cliente(
    var cpf: String,
    var nome: String,
    var telefone: String,
    var endereco: String,
    var instagram: String
) /*: Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cpf)
        parcel.writeString(nome)
        parcel.writeString(telefone)
        parcel.writeString(endereco)
        parcel.writeString(instagram)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        } */

{constructor() : this("", "", "", "", "")
}

