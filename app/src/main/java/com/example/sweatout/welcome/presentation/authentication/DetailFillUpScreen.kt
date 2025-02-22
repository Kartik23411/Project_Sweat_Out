package com.example.sweatout.welcome.presentation.authentication

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.authentication.components.CustomTextField
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow

@Composable
fun DetailsFillUpScreen(
    onCancelClick: () -> Unit,
    onProceedClick: () -> Unit,
    viewModel: WelcomeModuleViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val name = rememberSaveable { mutableStateOf<String?>("") }
    val username = rememberSaveable { mutableStateOf<String?>("") }
    val mobileNo = rememberSaveable { mutableStateOf<String?>("") }
    val photoUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val imagePicker =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
                if (uri != null) {
                    photoUri.value = uri
                    viewModel.updateImage(uri) // store temporary address in the viewmodel
                }
            }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeadlineText(textId = R.string.fill_details_headline)
        CustomAboutText(textId = R.string.fill_up_about)

        Column(
            modifier = Modifier
                    .fillMaxSize(.87f)
                    .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Top
        ) {

            ProfilePictureSelectionRow(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                photoUri = photoUri.value,
                onClick = {
                    imagePicker.launch(
                        PickVisualMediaRequest(
                            mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            )

            CustomTextField(
                value = name,
                onValueChange = { name.value = it },
                placeHolder = stringResource(R.string.name),
                imageVector = Icons.Default.Person,
                isPassword = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            Spacer(Modifier.height(24.dp))
            CustomTextField(
                value = username,
                onValueChange = { username.value = it },
                placeHolder = stringResource(R.string.username),
                imageVector = Icons.Default.Person,
                isPassword = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            Spacer(Modifier.height(24.dp))
            CustomTextField(
                value = mobileNo,
                onValueChange = { mobileNo.value = it },
                placeHolder = stringResource(R.string.name),
                imageVector = Icons.Default.Call,
                isPassword = false,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
        }

        WelcomeNavigationButtonRow(
            onCancel = {
                onCancelClick()
            },
            onProceed = {
                if (name.value != null && username.value != null && mobileNo.value != null) {
                    viewModel.updateName(name.value !!)
                    viewModel.updateUsername(username.value !!)
                    viewModel.updateMobileNo(mobileNo.value !!)
                    onProceedClick()
                }
                else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.fill_details_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
        )
    }
}


@Composable
fun ProfilePictureSelectionRow(
    modifier: Modifier = Modifier,
    photoUri: Uri?,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.BottomEnd
        ) {
            if (photoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(photoUri),
                    contentDescription = stringResource(R.string.profile_picture),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                )
            }
            else {
                Box(
                    modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest),

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


            IconButton(
                modifier = Modifier.size(35.dp),
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.primary),
                onClick = { onClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }

        }
    }
}