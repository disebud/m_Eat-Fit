//package com.weird.eat_n_fit.ui.sign.signUp
//
//import androidx.appcompat.app.AppCompatActivity
//import com.karumi.dexter.listener.single.PermissionListener
//
//class SignUpPhotoActivity : AppCompatActivity(), PermissionListener {
//
//        val REQUEST_IMAGE_CAPTURE = 1
//        var statusAdd:Boolean = false
//        lateinit var filePath: Uri
//
//        lateinit var storage: FirebaseStorage
//        lateinit var storageReference: StorageReference
//
//        lateinit var preferences: Preferences
//
//         var user : User? = null
//
//        private lateinit var mFirebaseDatabase: DatabaseReference
//        private lateinit var mFirebaseInstance: FirebaseDatabase
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_sign_up_photo)
//
//            preferences = Preferences(this)
//            storage = FirebaseStorage.getInstance()
//            storageReference = storage.getReference()
//
//            mFirebaseInstance = FirebaseDatabase.getInstance()
//            mFirebaseDatabase = mFirebaseInstance.getReference("User")
//
//            user = intent.getParcelableExtra("data")
//            tv_user.text = user?.user_f_name+" "+user?.user_l_name
//
//            iv_add.setOnClickListener {
//                if (statusAdd) {
//                    statusAdd = false
//                    save_next.visibility = View.INVISIBLE
//                    iv_add.setImageResource(R.drawable.ic_btn_upload)
//                    iv_profile.setImageResource(R.drawable.user_pic)
//
//                } else {
////                Dexter.withActivity(this)
////                    .withPermission(android.Manifest.permission.CAMERA)
////                    .withListener(this)
////                    .check()
//
//                    ImagePicker.with(this)
//                        .cameraOnly()	//User can only capture image using Camera
//                        .start()
//                }
//            }
//
//            upload_soon.setOnClickListener {
//
//                finishAffinity()
//
//                val intent = Intent(this@SignUpPhotoActivity,
//                    HomeActivity::class.java)
//                startActivity(intent)
//            }
//
//            save_next.setOnClickListener {
//                if (filePath != null) {
//                    val progressDialog = ProgressDialog(this)
//                    progressDialog.setTitle("Uploading...")
//                    progressDialog.show()
//
//                    val ref = storageReference.child("images/" + UUID.randomUUID().toString())
//                    ref.putFile(filePath)
//                        .addOnSuccessListener {
//                            progressDialog.dismiss()
//                            Toast.makeText(this@SignUpPhotoActivity, "Uploaded", Toast.LENGTH_SHORT).show()
//                            ref.downloadUrl.addOnSuccessListener {
//                                saveToFirebase(it.toString())
//                            }
//                        }
//                        .addOnFailureListener { e ->
//                            progressDialog.dismiss()
//                            Toast.makeText(this@SignUpPhotoActivity, "Failed " + e.message, Toast.LENGTH_SHORT).show()
//                        }
//                        .addOnProgressListener { taskSnapshot ->
//                            val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
//                        }
//                }
//
//            }
//        }
//
//        private fun saveToFirebase(url: String) {
//
//            mFirebaseDatabase.child(user.username!!).addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                    user.url = url
//                    mFirebaseDatabase.child(user.username!!).setValue(user)
//
//                    preferences.setValues("nama", user.nama.toString())
//                    preferences.setValues("user", user.username.toString())
//                    preferences.setValues("saldo", "")
//                    preferences.setValues("url", "")
//                    preferences.setValues("email", user.email.toString())
//                    preferences.setValues("status", "1")
//                    preferences.setValues("url", url)
//
//                    finishAffinity()
//                    val intent = Intent(this@SignUpPhotoActivity,
//                        HomeActivity::class.java)
//                    startActivity(intent)
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Toast.makeText(this@SignUpPhotoActivity, ""+error.message, Toast.LENGTH_LONG).show()
//                }
//            })
//
//
//        }
//
//        override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//            //To change body of created functions use File | Settings | File Templates.
////        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
////            takePictureIntent.resolveActivity(packageManager)?.also {
////                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
////            }
////        }
//
//            ImagePicker.with(this)
//                .cameraOnly()	//User can only capture image using Camera
//                .start()
//
//        }
//
//        override fun onPermissionRationaleShouldBeShown(
//            permission: com.karumi.dexter.listener.PermissionRequest?,
//            token: PermissionToken?
//        ) {
//            //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun onPermissionDenied(response: PermissionDeniedResponse?) {
//            //To change body of created functions use File | Settings | File Templates.
//            Toast.makeText(this, "Anda tidak bisa menambahkan photo profile", Toast.LENGTH_LONG ).show()
//        }
//
//        override fun onBackPressed() {
//            Toast.makeText(this, "Tergesah? Klik tombol Upload Nanti aja", Toast.LENGTH_LONG ).show()
//        }
//
////    @SuppressLint("MissingSuperCall")
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
////            var bitmap = data?.extras?.get("data") as Bitmap
////            statusAdd = true
////
////            filePath = data.getData()!!
////
////            Glide.with(this)
////                .load(bitmap)
////                .apply(RequestOptions.circleCropTransform())
////                .into(iv_profile)
////
////            btn_save.visibility = View.VISIBLE
////            iv_add.setImageResource(R.drawable.ic_btn_delete)
////        }
////    }
//
//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//            if (resultCode == Activity.RESULT_OK) {
//                //Image Uri will not be null for RESULT_OK
//                statusAdd = true
//                filePath = data?.data!!
//
//                // image with Glide for make Circle Image
//                Glide.with(this)
//                    .load(filePath)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(iv_profile)
//
//                save_next.visibility = View.VISIBLE
//                iv_add.setImageResource(R.drawable.ic_btn_delete)
//
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
//            }
//        }
//}