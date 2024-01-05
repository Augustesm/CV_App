package com.example.cvapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun ProfileScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val imageLogo = painterResource(R.drawable.logo)
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
//            .background(Color.Gray)
            .padding(top = 80.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Image(
            painter = imageLogo,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.displayMedium,
            //color = Color(Black),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 5.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.introduction),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Destination.AboutMe.route) }) {
            Text(
                text = stringResource(id=R.string.about_me),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id=R.string.number),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id=R.string.email),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id=R.string.group),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 50.dp)

        )
    }
}