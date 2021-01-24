package kr.h.emirim.sookhee.sfoid

data class Users(
    val results: List<User>
)

data class User(
    val id: Id,
    val picture: Picture,
    val name: Name,
    val dob: Dob,
    val gender: String,
    val nat: String,
    val email: String,
    val phone: String,
    val cell: String,
    val location: Location
)

data class Id(val value: String)
data class Picture(val large: String)
data class Name(val first: String, val last: String)
data class Dob(val age: Int)
data class Location(val city: String, val country: String, val coordinates: Coordinates)
data class Coordinates(val latitude: String, val longitude: String)