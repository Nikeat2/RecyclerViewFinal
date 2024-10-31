package com.example.recyclerviewfinal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private lateinit var recyclerView: RecyclerView
lateinit var adapter: ContactAdapter
lateinit var contactList: MutableList<Contacts>
private lateinit var fragmentDetails: ContactDetailsFragment

class ContactsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        val searchEditText: EditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        initView()


        adapter = ContactAdapter(contactList)



        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter




        adapter.setOnItemClickListener(object : ContactAdapter.onItemClickListener {
            override fun onClick(position: Int) {
                val contactName = contactList[position].contactName
                val contactSurname = contactList[position].contactSurname
                val contactPhone = contactList[position].contactPhone
                val contactPhoto = contactList[position].contactPhoto

                sendInfo(
                    contactName = contactName,
                    contactSurname = contactSurname,
                    contactPhone = contactPhone,
                    contactPhoto = contactPhoto
                )


                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragmentDetails)
                    .addToBackStack("gang").commit()

                setFragmentResultListener(CONTACT_NAME_ARG) { requestKey, bundle ->

                    val resultName = bundle.getString(ContactDetailsFragment.CONTACT_NAME_ARG)
                    val resultSurname = bundle.getString(ContactDetailsFragment.CONTACT_SURNAME_ARG)
                    val resultPhone = bundle.getString(ContactDetailsFragment.CONTACT_PHONE_ARG)

                    updateContactName(position, resultName.toString())
                    updateContactSurname(position, resultSurname.toString())
                    updateContactPhone(position, resultPhone.toString())
                }


            }

        })


    }

    private fun sendInfo(
        contactName: String, contactSurname: String,
        contactPhone: String, contactPhoto: String
    ) {

        fragmentDetails = ContactDetailsFragment.newInstance(
            contactName = contactName,
            contactSurname = contactSurname,
            contactPhone = contactPhone,
            contactPhoto = contactPhoto,
        )

    }

    private fun initView() {

        contactList = mutableListOf(

        Contacts("John", "Doe", "(123) 456-7890", "https://randomuser.me/api/portraits/men/1.jpg"),
        Contacts("Jane", "Smith", "(234) 567-8901", "https://randomuser.me/api/portraits/women/1.jpg"),
        Contacts("Alice", "Johnson", "(345) 678-9012", "https://randomuser.me/api/portraits/women/2.jpg"),
        Contacts("Bob", "Williams", "(456) 789-0123", "https://randomuser.me/api/portraits/men/2.jpg"),
        Contacts("Charlie", "Brown", "(567) 890-1234", "https://randomuser.me/api/portraits/men/3.jpg"),
        Contacts("David", "Jones", "(678) 901-2345", "https://randomuser.me/api/portraits/men/4.jpg"),
        Contacts("Eva", "Garcia", "(789) 012-3456", "https://randomuser.me/api/portraits/women/3.jpg"),
        Contacts("Fiona", "Martinez", "(890) 123-4567", "https://randomuser.me/api/portraits/women/4.jpg"),
        Contacts("George", "Anderson", "(901) 234-5678", "https://randomuser.me/api/portraits/men/5.jpg"),
        Contacts("Hannah", "Thomas", "(012) 345-6789", "https://randomuser.me/api/portraits/women/5.jpg"),
        Contacts("Ivan", "Taylor", "(111) 456-7890", "https://randomuser.me/api/portraits/men/6.jpg"),
        Contacts("Julia", "Moore", "(222) 567-8901", "https://randomuser.me/api/portraits/women/6.jpg"),
        Contacts("Kevin", "Jackson", "(333) 678-9012", "https://randomuser.me/api/portraits/men/7.jpg"),
        Contacts("Lily", "Martin", "(444) 789-0123", "https://randomuser.me/api/portraits/women/7.jpg"),
        Contacts("Michael", "Lee", "(555) 890-1234", "https://randomuser.me/api/portraits/men/8.jpg"),
        Contacts("Nina", "Clark", "(666) 901-2345", "https://randomuser.me/api/portraits/women/8.jpg"),
        Contacts("Oscar", "Hernandez", "(777) 012-3456", "https://randomuser.me/api/portraits/men/9.jpg"),
        Contacts("Paula", "Robinson", "(888) 123-4567", "https://randomuser.me/api/portraits/women/9.jpg"),
        Contacts("Quinn", "Lewis", "(999) 234-5678", "https://randomuser.me/api/portraits/men/10.jpg"),
        Contacts("Ray", "Walker", "(000) 345-6789", "https://randomuser.me/api/portraits/men/11.jpg"),
        Contacts("Sarah", "Hall", "(101) 456-7890", "https://randomuser.me/api/portraits/women/10.jpg"),
        Contacts("Tom", "Allen", "(202) 567-8901", "https://randomuser.me/api/portraits/men/12.jpg"),
        Contacts("Uma", "Young", "(303) 678-9012", "https://randomuser.me/api/portraits/women/11.jpg"),
        Contacts("Vince", "King", "(404) 789-0123", "https://randomuser.me/api/portraits/men/13.jpg"),
        Contacts("Wendy", "Wright", "(505) 890-1234", "https://randomuser.me/api/portraits/women/12.jpg"),
        Contacts("Xander", "Scott", "(606) 901-2345", "https://randomuser.me/api/portraits/men/14.jpg"),
        Contacts("Yara", "Green", "(707) 012-3456", "https://randomuser.me/api/portraits/women/13.jpg"),
        Contacts("Zach", "Adams", "(808) 123-4567", "https://randomuser.me/api/portraits/men/15.jpg"),
        Contacts("Anna", "Bell", "(909) 234-5678", "https://randomuser.me/api/portraits/women/14.jpg"),
        Contacts("Brian", "Carter", "(010) 345-6789", "https://randomuser.me/api/portraits/men/16.jpg"),
        Contacts("Cathy", "Kim", "(111) 456-7890", "https://randomuser.me/api/portraits/women/15.jpg"),
        Contacts("Derek", "Davis", "(222) 567-8901", "https://randomuser.me/api/portraits/men/17.jpg"),
        Contacts("Ella", "Garcia", "(333) 678-9012", "https://randomuser.me/api/portraits/women/16.jpg"),
        Contacts("Frank", "Green", "(444) 789-0123", "https://randomuser.me/api/portraits/men/18.jpg"),
        Contacts("Grace", "Roberts", "(555) 890-1234", "https://randomuser.me/api/portraits/women/17.jpg"),
        Contacts("Harry", "Taylor", "(666) 901-2345", "https://randomuser.me/api/portraits/men/19.jpg"),
        Contacts("Ivy", "Martinez", "(777) 012-3456", "https://randomuser.me/api/portraits/women/18.jpg"),
        Contacts("Jack", "Hernandez", "(888) 123-4567", "https://randomuser.me/api/portraits/men/20.jpg"),
        Contacts("Kathy", "Lee", "(999) 234-5678", "https://randomuser.me/api/portraits/women/19.jpg"),
        Contacts("Leo", "King", "(010) 345-6789", "https://randomuser.me/api/portraits/men/21.jpg"),
        Contacts("Maya", "Scott", "(121) 456-7890", "https://randomuser.me/api/portraits/women/20.jpg"),
        Contacts("Nate", "Hall", "(232) 567-8901", "https://randomuser.me/api/portraits/men/22.jpg"),
        Contacts("Olivia", "Young", "(343) 678-9012", "https://randomuser.me/api/portraits/women/21.jpg"),
        Contacts("Paul", "Jackson", "(454) 789-0123", "https://randomuser.me/api/portraits/men/23.jpg"),
        Contacts("Rita", "Adams", "(565) 890-1234", "https://randomuser.me/api/portraits/women/22.jpg"),
        Contacts("Sam", "Moore", "(676) 901-2345", "https://randomuser.me/api/portraits/men/24.jpg"),
        Contacts("Tina", "Clark", "(787) 012-3456", "https://randomuser.me/api/portraits/women/23.jpg"),
        Contacts("Ursula", "Carter", "(898) 123-4567", "https://randomuser.me/api/portraits/women/24.jpg"),
        Contacts("Vera", "Martinez", "(909) 234-5678", "https://randomuser.me/api/portraits/women/25.jpg"),
        Contacts("Walter", "Baker", "(101) 345-6789", "https://randomuser.me/api/portraits/men/25.jpg"),
        Contacts("Xena", "Lopez", "(212) 456-7890", "https://randomuser.me/api/portraits/women/26.jpg"),
        Contacts("Yvonne", "Jones", "(323) 567-8901", "https://randomuser.me/api/portraits/women/27.jpg"),
        Contacts("Zane", "Hernandez", "(434) 678-9012", "https://randomuser.me/api/portraits/men/26.jpg"),
        Contacts("Ava", "Perez", "(545) 789-0123", "https://randomuser.me/api/portraits/women/28.jpg"),
        Contacts("Bryan", "Hall", "(656) 890-1234", "https://randomuser.me/api/portraits/men/27.jpg"),
        Contacts("Clara", "Garcia", "(767) 901-2345", "https://randomuser.me/api/portraits/women/29.jpg"),
        Contacts("Daniel", "Rodriguez", "(878) 012-3456", "https://randomuser.me/api/portraits/men/28.jpg"),
        Contacts("Emily", "Flores", "(989) 123-4567", "https://randomuser.me/api/portraits/women/30.jpg"),
        Contacts("Felix", "Gonzalez", "(090) 234-5678", "https://randomuser.me/api/portraits/men/29.jpg"),
        Contacts("Gina", "Lee", "(101) 345-6789", "https://randomuser.me/api/portraits/women/31.jpg"),
        Contacts("Henry", "Martinez", "(212) 456-7890", "https://randomuser.me/api/portraits/men/30.jpg"),
        Contacts("Iris", "Murphy", "(323) 567-8901", "https://randomuser.me/api/portraits/women/32.jpg"),
        Contacts("Jack", "Jones", "(434) 678-9012", "https://randomuser.me/api/portraits/men/31.jpg"),
        Contacts("Kara", "Butler", "(545) 789-0123", "https://randomuser.me/api/portraits/women/33.jpg"),
        Contacts("Leo", "Bell", "(656) 890-1234", "https://randomuser.me/api/portraits/men/32.jpg"),
        Contacts("Molly", "Davis", "(767) 901-2345", "https://randomuser.me/api/portraits/women/34.jpg"),
        Contacts("Nico", "Clark", "(878) 012-3456", "https://randomuser.me/api/portraits/men/33.jpg"),
        Contacts("Opal", "Wilson", "(989) 123-4567", "https://randomuser.me/api/portraits/women/35.jpg"),
        Contacts("Pete", "Gilbert", "(090) 234-5678", "https://randomuser.me/api/portraits/men/34.jpg"),
        Contacts("Quinn", "Edwards", "(101) 345-6789", "https://randomuser.me/api/portraits/women/36.jpg"),
        Contacts("Ralph", "Hernandez", "(212) 456-7890", "https://randomuser.me/api/portraits/men/35.jpg"),
        Contacts("Sophie", "Kim", "(323) 567-8901", "https://randomuser.me/api/portraits/women/37.jpg"),
        Contacts("Tom", "Palmer", "(434) 678-9012", "https://randomuser.me/api/portraits/men/36.jpg"),
        Contacts("Ulysses", "Foster", "(545) 789-0123", "https://randomuser.me/api/portraits/men/37.jpg"),
        Contacts("Violet", "Mason", "(656) 890-1234", "https://randomuser.me/api/portraits/women/38.jpg"),
        Contacts("Walter", "Rivers", "(767) 901-2345", "https://randomuser.me/api/portraits/men/38.jpg"),
        Contacts("Xena", "Ellis", "(878) 012-3456", "https://randomuser.me/api/portraits/women/39.jpg"),
        Contacts("Yardley", "Hughes", "(989) 123-4567", "https://randomuser.me/api/portraits/men/39.jpg"),
        Contacts("Zara", "Woods", "(090) 234-5678", "https://randomuser.me/api/portraits/women/40.jpg")
        )




    }

    fun updateContactName(position: Int, newName: String) {
        if (position in contactList.indices) {
            contactList[position].contactName = newName
            adapter.updateContactName(position, newName)
        }
    }

    fun updateContactSurname(position: Int, newSurname: String) {
        if (position in contactList.indices) {
            contactList[position].contactSurname = newSurname
            adapter.updateContactSurname(position, newSurname)
        }
    }
    fun updateContactPhone(position: Int, newPhone: String) {
        if (position in contactList.indices) {
            contactList[position].contactPhone = newPhone
            adapter.updateContactPhone(position, newPhone)
        }
    }

    companion object {
        private const val CONTACT_PHOTO_ARG = "contact_photo_arg"
        private const val CONTACT_SURNAME_ARG = "contact_surname_arg"
        private const val CONTACT_NAME_ARG = "contact_name_arg"
        private const val CONTACT_PHONE_ARG = "contact_phone_arg"
    }

}