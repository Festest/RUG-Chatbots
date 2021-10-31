package Model.Chatrooms;

public class Main {

	/**
	 * Initialise and start chatroom(s)
	 * @param args
	 */
	public static void main(String[] args){
		Model.Chatrooms.Chatroom chatroom = new Model.Chatrooms.Chatroom();
		Model.Chatrooms.Chatroom chatroom2 = new Model.Chatrooms.Chatroom();
		Model.Chatrooms.Chatroom chatroom3 = new Model.Chatrooms.Chatroom();

		chatroom.start();
		chatroom2.start();
		chatroom3.start();
	}
}
