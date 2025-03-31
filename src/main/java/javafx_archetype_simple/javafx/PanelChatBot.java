package javafx_archetype_simple.javafx;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PanelChatBot extends GridPane {

	//Constantes para la peticion a la api de openrouter - deepseek free
	private final String API_KEY = "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";
	private final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
	private final String API_MODEL = "deepseek/deepseek-chat-v3-0324:free";

	//Barra lateral de la conversacion
	private ScrollPane scrollChat;
	//Texto contenedor de la conversacion con el bot
	private Text chatHistory;
	//Dialogo para hablar con el chatbot
	private TextField inputField;
	//Boton para mandar la conversacion
	private Button sendButton;

	public PanelChatBot() {
		//Situamos en el centro los elementos el grid
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		scrollChat = new ScrollPane();
		chatHistory = new Text("Buenos Dias!!");
		inputField = new TextField();
		sendButton = new Button("Enviar");
		//Al poner a true defaultbutton recibe el evento de pulsar intro como si hubieran pulsado el boton
		sendButton.setDefaultButton(true);

		//Metemos en el scrollPane el text para que tenga barra lateral
		scrollChat.setContent(chatHistory);
		scrollChat.setPrefSize(500, 400);
		inputField.setPrefWidth(450);

		//Posicionamos los elementos
		this.add(new Label("Chat:"), 0, 0);
		this.add(scrollChat, 0, 1, 2, 1);
		this.add(inputField, 0, 2);
		this.add(sendButton, 1, 2);

		sendButton.setOnAction(e -> this.sendToOpenRouter(""));

	}

	public void sendToOpenRouter(String mensaje) {

		System.out.println("Enviamos peticion al deepseek...");

		/*
			 * {
			 * "model": "deepseek/deepseek-chat-v3-0324:free",
		"messages": [
		  {
		    "role": "user",
		    "content": "What is the meaning of life?"
		  }
		],
		
		})
		 */

		//Creamos la estructura json necesaria para la solicitud post
		//a la api de openroute
		JsonObject jsonBody = new JsonObject();
		//Añadimos el modelo al json
		jsonBody.addProperty("model", this.API_MODEL);
		//Creamos un array para los mensajes
		JsonArray messages = new JsonArray();
		JsonObject message = new JsonObject();
		//añadimos las propiedades
		message.addProperty("role", "user");
		message.addProperty("content",
				"Explicame el nacimiento del primer informatico de la historia en clave de humor");
		//Añadimos el message al array messages
		messages.add(message);
		//Añadimos al json principal el array con role y content como un elemento mas
		jsonBody.add("messages", messages);

		/* Header structure
		 * "Authorization": "Bearer <OPENROUTER_API_KEY>",
			"Content-Type": "application/json",
		 */
		//Realizamos la solicitud post http
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.API_URL))
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + this.API_KEY)
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString())).build();

		//Creamos un cliente para recoger la respuesta
		HttpClient client = HttpClient.newHttpClient();

		client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
				.thenAccept(response -> {

					System.out.println("Respuesta:" + response);

				});

	}

}
