package com.chatBot.model;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ChatBotVO {
	//Primary key msg_no
		private Integer qes_no;
		private String qes_title;
		private String qes_ans;
		private String qes_comp;
		private Integer qes_class;
		
		
		public static void main(String[] args) {
			ChatBotVO vo = new ChatBotVO();
			String i = vo.getQes_ans();
		}


		
		
}
