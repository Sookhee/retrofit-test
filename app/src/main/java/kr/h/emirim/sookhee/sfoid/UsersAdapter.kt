package kr.h.emirim.sookhee.sfoid

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        gender.text = if(user.gender == "male") "male" else "female"
        nat.text = getNat(user.nat)
        email.text = user.email
        phone.text = user.phone
        cell.text = user.cell
    }
}

fun getNat(nat: String): String{
    return nat
}