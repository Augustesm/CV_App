package com.example.cvapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.Locale
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen(navController: NavHostController) {
    var contactInfo by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()), // Add verticalScroll modifier
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.hello),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(vertical = 50.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
        Button(
            onClick = { navController.navigate(Destination.Profile.route) },
            modifier = buttonModifier
            ) {
            Text(
                text = stringResource(id = R.string.button1),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Destination.Portfolio.route) },
            modifier = buttonModifier
        ) {
            Text(
                text = stringResource(id = R.string.button2),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        ContactMeForm(contactInfo) {
            contactInfo = it
        }
        Spacer(modifier = Modifier.height(16.dp))
        SendEmailButton(contactInfo, context)

        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            EnglishLanguageButton(context)
            LithuanianLanguageButton(context)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Destination.QRcode.route) },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .size(width = 80.dp, height = 40.dp) // Adjust the size here
        ) {
            Text("QR")
        }
    }
}

@Composable
private fun EnglishLanguageButton(context: Context) {
    Button(
        onClick = {
            updateLocale(context, "en")
        },
        modifier = Modifier
            .padding(vertical = 2.dp)
            .size(width = 80.dp, height = 40.dp) // Adjust the size here
    ) {
        Text("EN")
    }
}

@Composable
private fun LithuanianLanguageButton(context: Context) {
    Button(
        onClick = {
            updateLocale(context, "lt")
        },
        modifier = Modifier
            .padding(vertical = 2.dp)
            .size(width = 80.dp, height = 40.dp) // Adjust the size here
    ) {
        Text("LT")
    }
}

private fun updateLocale(context: Context, languageCode: String) {
    val newLocale = Locale(languageCode)

    val resources = context.resources
    val configuration = Configuration(resources.configuration)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        configuration.setLocale(newLocale)
    } else {
        configuration.locale = newLocale
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        configuration.setLayoutDirection(newLocale)
    }

    resources.updateConfiguration(configuration, resources.displayMetrics)

    // Restart the current activity to apply the changes
    val intent = Intent(context, context.javaClass)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}

@Composable
private fun ContactMeForm(contactInfo: String, onContactInfoChange: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = inputText,
            onValueChange = {
                inputText = it
                onContactInfoChange(it)
            },
            label = { Text(stringResource(id = R.string.contact)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )
    }
}


@Composable
private fun SendEmailButton(contactInfo: String, context: Context) {
    Button(
        onClick = {
            // Placeholder logic, replace with actual email sending logic
            sendEmail(contactInfo, context)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.button3),
            style = MaterialTheme.typography.displayMedium,
        )
    }
}

private fun sendEmail(contactInfo: String, context: Context) {
    // Placeholder function for sending email. Replace it with your actual logic for sending emails.
    // Note: Sending emails from a mobile app usually requires a backend service to handle emails.
    // You may want to use a service like Firebase Cloud Functions, AWS Lambda, or your own backend.
    // This example assumes a simplified scenario for demonstration purposes.

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "plain/text"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("smols2001@email.com"))
    intent.putExtra(Intent.EXTRA_SUBJECT, "Contact Me Form")
    intent.putExtra(Intent.EXTRA_TEXT, contactInfo)
    context.startActivity(intent)
}

