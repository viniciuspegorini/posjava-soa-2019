package br.edu.utfpr.pb.posjava3auth.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ResponseToken {

	@Getter @Setter
	private String access_token;

}
