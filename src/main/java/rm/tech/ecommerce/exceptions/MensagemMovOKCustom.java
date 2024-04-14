package rm.tech.ecommerce.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Builder @Data @AllArgsConstructor @NoArgsConstructor class MensagemMovOKCustom {

	private String datahora;
	private Integer status;
	private Integer codigo;
	private String mensagem;
	private List<Dado> dados;

	@Data
	public class Dado {

		private Situacao situacao;
		private String situacaoCor;
		private String protocolo;
		private String status;
		private String statusCor;
		private String statusAnterior;
		private String statusAnteriorCor;
		private String data;
		private Long processoId;
		private String mensagem;

	}

	public enum Situacao {

		CONCLUIDO("#009241"), ERRO("#F54527");
		
		private String cor;
		
		Situacao(String cor) {
			this.cor = cor;
		}
		
		public String getCor() {
			return cor;
		}
	}
}