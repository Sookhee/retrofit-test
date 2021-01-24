package kr.h.emirim.sookhee.sfoid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsersAdapter(val users: List<User>): RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        return holder.bind(users[position])
    }
}

class UsersViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val card: CardView = itemView.findViewById(R.id.user_card)
    private val photo: ImageView = itemView.findViewById(R.id.user_image)
    private val name: TextView = itemView.findViewById(R.id.user_name)
    private val age:TextView = itemView.findViewById(R.id.user_age)
    private val gender:TextView = itemView.findViewById(R.id.user_gender)
    private val nat:TextView = itemView.findViewById(R.id.user_nat)
    private val email:TextView = itemView.findViewById(R.id.user_email)
    private val phone:TextView = itemView.findViewById(R.id.user_phone)
    private val cell:TextView = itemView.findViewById(R.id.user_cell)

    fun bind(user: User) {
        Glide.with(itemView.context).load(user.picture.large)
                .centerCrop()
                .circleCrop()
                .into(photo)
        name.text = "${user.name.first} ${user.name.last}"
        age.text = "(${user.dob.age})"
        gender.text = getGenderCode(user.gender)
        nat.text = getNatCode(user.nat)
        email.text = "\uD83D\uDCE7 " + user.email
        phone.text = "\u260E\uFE0F " + user.phone
        cell.text = "\uD83D\uDCF1 " + user.cell

        card.setOnClickListener{
            val intent: Intent
            intent = Intent(itemView.context, UserDetailActivity::class.java)

            intent.putExtra("image", user.picture.large)
            intent.putExtra("name", "${user.name.first} ${user.name.last}")
            intent.putExtra("age", "${user.dob.age}" )
            intent.putExtra("gender", getGenderCode(user.gender))
            intent.putExtra("nat", getNatCode(user.nat))
            intent.putExtra("phone", user.phone)
            intent.putExtra("cell", user.cell)
            intent.putExtra("email", user.email)
            intent.putExtra("country", user.location.country)
            intent.putExtra("city", user.location.city)
            intent.putExtra("latitude", user.location.coordinates.latitude)
            intent.putExtra("longitude", user.location.coordinates.longitude)


            itemView.context.startActivity(intent)

        }
    }
}

fun getNatCode(nat: String): String{
    val result= when(nat){
        "AU" -> "\uD83C\uDDE6\uD83C\uDDFA"
        "BR" -> "\uD83C\uDDE7\uD83C\uDDF7"
        "CA" -> "\uD83C\uDDE8\uD83C\uDDE6"
        "CH" -> "\uD83C\uDDE8\uD83C\uDDED"
        "DE" -> "\uD83C\uDDE9\uD83C\uDDEA"
        "DK" -> "\uD83C\uDDE9\uD83C\uDDF0"
        "ES" -> "\uD83C\uDDEA\uD83C\uDDF8"
        "FI" -> "\uD83C\uDDEB\uD83C\uDDEE"
        "FR" -> "\uD83C\uDDEB\uD83C\uDDF7"
        "GB" -> "\uD83C\uDDEC\uD83C\uDDE7"
        "IE" -> "\uD83C\uDDEE\uD83C\uDDEA"
        "IR" -> "\uD83C\uDDEE\uD83C\uDDF7"
        "NO" -> "\uD83C\uDDF3\uD83C\uDDF4"
        "NL" -> "\uD83C\uDDF3\uD83C\uDDF4"
        "NZ" -> "\uD83C\uDDF3\uD83C\uDDFF"
        "TR" -> "\uD83C\uDDF9\uD83C\uDDF7"
        "US" -> "\uD83C\uDDF9\uD83C\uDDF7"
        else -> ""
    }

    return result
}

fun getGenderCode(gender: String): String{
    val result = when(gender){
        "male" -> "\uD83D\uDE4B\u200D\u2642\uFE0F"
        "female" -> "\uD83D\uDE4B\u200D\u2640\uFE0F"
        else -> ""
    }

    return result
}
