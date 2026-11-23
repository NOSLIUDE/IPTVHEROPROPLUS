package com.iptvhero.pro.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iptvhero.pro.R
import com.iptvhero.pro.ui.theme.DcBlue
import com.iptvhero.pro.ui.theme.GoldFocus
import com.iptvhero.pro.ui.theme.MarvelRed
import com.iptvhero.pro.ui.theme.MarvelYellow
import com.iptvhero.pro.ui.theme.TextDim
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2500) // Aumentei um pouco o tempo para o usuário apreciar a arte
        onFinished()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "splash")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.03f, // Reduzi a pulsação para não distorcer muito a imagem
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse),
        label = "pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Fundo escuro combinando com as bordas da imagem
                Brush.radialGradient(
                    colors = listOf(Color(0xFF0F0A1A), Color(0xFF05050B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            // IMAGEM PRINCIPAL
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "IPTV Hero Pro+ Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(pulse) // Efeito de respiração suave
                    .clip(RoundedCornerShape(24.dp)), // Bordas arredondadas para combinar com a arte
                contentScale = ContentScale.Fit // Mantém a proporção da imagem
            )

            // O texto "IPTV HERO PRO+" foi removido pois já está na imagem.
            // Mantive apenas o subtítulo e a barra de progresso para dar um visual "premium".

            Text(
                text = "MULTIVERSE • LIVE • VOD",
                color = TextDim,
                fontSize = 12.sp,
                letterSpacing = 5.sp,
                fontWeight = FontWeight.Bold
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .width(260.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = GoldFocus,
                trackColor = Color.White.copy(alpha = 0.1f)
            )
        }
    }
}
