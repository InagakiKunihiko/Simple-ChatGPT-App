import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGPTInterface {
    public ChatGPTInterface(){
        //chatApiAccessのインスタンス化
        ChatApiAccess chatApiAccess = new ChatApiAccess();
        //全体の画面設定
        JFrame frame = new JFrame("ChatGPT Interface");
        frame.setSize(500, 400);
        frame.setVisible(true);

        //出力箇所
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setWrapStyleWord(true);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        //入力箇所
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        //送信ボタンを押したときの処理
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                chatArea.append("You: " + userInput + "\n");
                String chatGPTResponse = "ChatGPT: " + chatApiAccess.getResponse(userInput) + "\n";
                chatArea.append(chatGPTResponse);
                inputField.setText("");
            }
        });

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }
}
