package com.example.cvapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cvapp.data.Datasource
import com.example.cvapp.model.Portfolio


@Composable
fun PortfolioScreen(navController: NavHostController) {
    PortfolioList(
        portfolioList = Datasource().loadPortfolio(),
    )
}

@Composable
fun PortfolioList(portfolioList: List<Portfolio>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(portfolioList) { portfolio ->
            PortfolioCard(
                portfolio = portfolio,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Composable
fun PortfolioCard(portfolio: Portfolio, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(portfolio.imageResourceId),
                contentDescription = stringResource(portfolio.stringResourceId),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(portfolio.stringResourceId),
                modifier = Modifier.padding(25.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}
