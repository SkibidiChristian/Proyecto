package com.example.ios_practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ios_practica1.ui.theme.IOSPractica1Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize

// ...

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IOSPractica1Theme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}
data class Message(val author: String, val body: String)

// ...


@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.perropanzon),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = "",
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
                onTextLayout = {}
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    onTextLayout = {}
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    IOSPractica1Theme {
        Conversation(SampleData.conversationSample)
    }
}

/**
 * com.example.ios_practica1.SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Perro Panzon",
            "Perro panzon"
        ),
        Message(
            "Perro Panzon",
            """Todo chiquito""".trim()
        ),
        Message(
            "Perro Panzon",
            "Todo panzon"
        ),
        Message(
            "Perro Panzon",
            """Un perro pequeñito, con pelaje tan suave,""".trim()
        ),
        Message(
            "Perro Panzon",
            "su tamaño diminuto, pero su amor, tan grande."
        ),
        Message(
            "Perro Panzon",
            "Con ojos chispeantes, mirada juguetona,"
        ),
        Message(
            "Perro Panzon",
            "corre por la casa, ¡es toda una persona!"
        ),
        Message(
            "Perro Panzon",
            "Su pancita redonda, llena de alegría,"
        ),
        Message(
            "Perro Panzon",
            "cuando busca caricias, ¡nunca se cansaría!"
        ),
        Message(
            "Perro Panzon",
            "Con ladridos tiernos, nos llena de emoción,"
        ),
        Message(
            "Perro Panzon",
            "el perro chiquito, ¡es todo un campeón!"
        ),
        Message(
            "Perro Panzon",
            "Entre almohadas y mantas, encuentra su lugar,"
        ),
        Message(
            "Perro Panzon",
            "un rey en su castillo, ¡nadie lo puede igualar!"
        ),
        Message(
            "Perro Panzon",
            "Aunque sea pequeñito, su corazón es gigante,"
        ),
        Message(
            "Perro Panzon",
            "el perro chiquito, ¡es todo un amante!"
        ),
    )
}
