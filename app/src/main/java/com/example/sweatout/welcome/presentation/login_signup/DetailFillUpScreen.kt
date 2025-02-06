package com.example.sweatout.welcome.presentation.login_signup

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.contentReceiver
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.view.accessibility.AccessibilityViewCommand.MoveAtGranularityArguments
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sweatout.R
import com.example.sweatout.welcome.presentation.WelcomeModuleViewModel
import com.example.sweatout.welcome.presentation.components.CustomAboutText
import com.example.sweatout.welcome.presentation.components.CustomHeadlineText
import com.example.sweatout.welcome.presentation.components.WelcomeNavigationButtonRow
import com.example.sweatout.welcome.presentation.login_signup.components.CustomTextField
import java.net.URI

@Composable
fun DetailsFillUpScreen(
    onSkipClick: () -> Unit,
    onProceedClick: () -> Unit,
    viewModel: WelcomeModuleViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val name = rememberSaveable { mutableStateOf<String?>("") }
    val username = rememberSaveable { mutableStateOf<String?>("") }
    val mobileNo = rememberSaveable { mutableStateOf<String?>("") }
    val photoUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri!=null){
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
                onClick = {imagePicker.launch(PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                ))}
            )

            CustomTextField(
                value = name,
                onValueChange = {name.value = it},
                placeHolder = stringResource(R.string.name),
                imageVector = Icons.Default.Person,
                isPassword = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            Spacer(Modifier.height(24.dp))
            CustomTextField(
                value = username,
                onValueChange = {username.value = it},
                placeHolder = stringResource(R.string.username),
                imageVector = Icons.Default.Person,
                isPassword = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            Spacer(Modifier.height(24.dp))
            CustomTextField(
                value = mobileNo,
                onValueChange = {mobileNo.value = it},
                placeHolder = stringResource(R.string.name),
                imageVector = Icons.Default.Call,
                isPassword = false,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
        }

        WelcomeNavigationButtonRow(
            onCancel = {
                onSkipClick()
            },
            onProceed = {
                onProceedClick()
            },
        )
    }
}


@Composable
fun ProfilePictureSelectionRow(
    modifier: Modifier = Modifier,
    photoUri: Uri?,
    onClick:()->Unit
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
        ){
            if (photoUri!=null){
                Image(
                    painter = rememberAsyncImagePainter(photoUri),
                    contentDescription = stringResource(R.string.profile_picture),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                )
            }else{
                Box(
                    modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest),

                    contentAlignment = Alignment.Center
                ){
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