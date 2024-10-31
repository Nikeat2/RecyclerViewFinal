package com.example.recyclerviewfinal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide


private var contactName: String? = null
private var contactSurname: String? = null
private var contactPhoto: String? = null
private var contactPhone: String? = null


class ContactDetailsFragment : Fragment() {

    private lateinit var changefragment: ChangeContactFragment

    private lateinit var detailedContactBtn: Button
    private lateinit var detailedContactName: TextView
    private lateinit var detailedContactSurname: TextView
    private lateinit var detailedContactNumber: TextView

    private fun initViewForFragment(view: View) {

        detailedContactSurname = view.findViewById(R.id.detailedContactSurname)
        detailedContactName = view.findViewById(R.id.detailedContactName)
        detailedContactNumber = view.findViewById(R.id.detailedContactNumber)
        val detailedContactPhoto = view.findViewById<ImageView>(R.id.detailedContactPhoto)
        detailedContactBtn = view.findViewById(R.id.changeContactBtn)

        detailedContactSurname.text = contactSurname
        detailedContactName.text = contactName
        detailedContactNumber.text = contactPhone
        Glide.with(this).load(contactPhoto).into(detailedContactPhoto)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactName = it.getString(CONTACT_NAME_ARG)
            contactSurname = it.getString(CONTACT_SURNAME_ARG)
            contactPhone = it.getString(CONTACT_PHONE_ARG)
            contactPhoto = it.getString(CONTACT_PHOTO_ARG)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_details, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewForFragment(view)

        detailedContactBtn.setOnClickListener {
            btnClick(
                contactName = contactName.toString(),
                contactSurname = contactSurname.toString(),
                contactPhone = contactPhone.toString(),
                contactPhoto = contactPhoto.toString()
            )
        }


    }

    companion object {
        private const val CONTACT_PHOTO_ARG = "contact_photo_arg"
        const val CONTACT_SURNAME_ARG = "contact_surname_arg"
        const val CONTACT_NAME_ARG = "contact_name_arg"
        const val CONTACT_PHONE_ARG = "contact_phone_arg"

        @JvmStatic
        fun newInstance(
            contactName: String, contactSurname: String,
            contactPhone: String, contactPhoto: String
        ) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_PHOTO_ARG, contactPhoto)
                    putString(CONTACT_SURNAME_ARG, contactSurname)
                    putString(CONTACT_NAME_ARG, contactName)
                    putString(CONTACT_PHONE_ARG, contactPhone)
                }
            }

    }

    private fun btnClick(
        contactName: String, contactSurname: String,
        contactPhone: String, contactPhoto: String
    ) {

        changefragment = ChangeContactFragment.newInstance(
            contactName = contactName,
            contactPhone = contactPhone,
            contactPhoto = contactPhoto,
            contactSurname = contactSurname
        )

        parentFragmentManager.beginTransaction().add(R.id.fragmentContainerView, changefragment)
            .commit()
        parentFragmentManager.beginTransaction().remove(this)
    }


}

