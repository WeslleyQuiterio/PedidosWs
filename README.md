# PedidosWs
PedidoWs é um WebService Java - Ativividade Universitária
Entrega do projeto Final da matéria: Backend

#O Banco de dados será Mysql 5.6.xx

#Script de criação do banco e das tabelas se encontra dentro do repositório
 - https://github.com/WeslleyQuiterio/PedidosWs/blob/main/SqlCreateDataBase.sql

#Endereço, Usuário e Senha do Mysql:
 - O WebService irá procurar o banco de dados em "localhost" na porta 3306
 - O Banco de dados terá o nome de "pedidos_online"
 - Usuário: "pedidos" e senha: "123456"
 
 obs: Caso queira usar outro usuário, favor modificar a classe "ConectaDB.java" dentro do pacote "br.com.pedidosonline.dao" com o usuário e senha desejado
 é possivel também indicar um outro servidor.
 O Nome do banco de dados não pode ser alterado
 
#Tecnologias utilizadas:
 - Java 8
 - Netbeans 8.2
 - Tomcat 8.5
 - MySQL 5.6
 - Bibliotecas Java:
  - Jersey 2.5.1
  - Gson 2.8.2
  - JAX-RS 2.0
  - Driver JDBC do MySQL

#Java 8 como VM
 - As Variáveis de ambiente deverão estar configuradas no dispositivo
 - O JDK do java 8 deverá ser instalado juntamente com o Netbeans 8.2

{servidor} = endereço do servidor onde foi instaldo o PedidoOnlineWs - "localhost"
{porta} = porta do servidor onde foi instaldo o PedidoOnlineWs - "8080"

#Endereços dos Endpoints
	{servidor} = endereço do servidor onde foi instaldo o PedidoOnlineWs - "localhost"
	{porta} = porta do servidor onde foi instaldo o PedidoOnlineWs - "8080"
 - produtos
 
   * GET http://{servidor}:{porta}/PedidoOnlineWs/api/produtos
   - Exemplo Retorno, content:
		
	
              
        
		[
			{
			"codBarras":"14632",
			"descricaoCompleta":"Teste produto novo",
			"descricaoResumida":"tst produto",
			"fator":1.0,
			"id":2,
			"unidade":"und",
			"valorUnitario":175.95
		  }
		,
		  
		  {
			"codBarras":"54321",
			"descricaoCompleta":"Entrega trabalho final",
			"descricaoResumida":"Ent Trb final",
			"fator":12.0,
			"id":3,
			"unidade":"und",
			"valorUnitario":1200.0
		  }
		]
		
		
		
	* POST http://{servidor}:{porta}/PedidoOnlineWs/api/produtos
	deverá ser enviado um body com os dados do produto a ser incluido
      - Exemplo Body Exemplo: 
		
		{
		  "codBarras":"14632",
		  "descricaoCompleta":"Teste produto novo",
		  "descricaoResumida":"tst produto",
		  "fator":1.0,		 
		  "unidade":"und",
		  "valorUnitario":175.95
		}	
		
		
		
   * GET http://{servidor}:{porta}/PedidoOnlineWs/api/produtos/{idProduto}
   - Exemplo Retorno, content:
		
		{
		  "codBarras":"14632",
		  "descricaoCompleta":"Teste produto novo",
		  "descricaoResumida":"tst produto",
		  "fator":1.0,
		  "id":2,
		  "unidade":"und",
		  "valorUnitario":175.95
		}
		
		
		
   * PUT http://{servidor}:{porta}/PedidoOnlineWs/api/produtos/{idProduto}
	deverá ser enviado um body com os dados do produto a ser alterado
   - Body Exemplo: 
		
		{
		  "codBarras":"14632",
		  "descricaoCompleta":"Teste produto novo",
		  "descricaoResumida":"tst produto",
		  "fator":1.0,		 
		  "unidade":"und",
		  "valorUnitario":175.95
		}
		
	Em caso de sucesso será retornado o responseCode 204

	

	* DELETE http://{servidor}:{porta}/PedidoOnlineWs/api/produtos/{idProduto}
	  Em caso de sucesso será retornado o responseCode 204
	


 - pedidos
 
   * GET http://{servidor}:{porta}/PedidoOnlineWs/api/pedidos
   - Exemplo Retorno, content:
		
		
		[
			{
			"clienteMesaComanda":"",
			"dataCriacao":"2021-05-29T00:00:00-03:00",
			"desconto":1.00,
			"idpedido":3,
			"itens":
			[
					{
				"desconto":0.5,
				"idpedido":3,
				"idpedidoitem":3,
				"idproduto":2,
				"produto":
				{
				  "codBarras":"14632",
				  "descricaoCompleta":"Teste produto novo",
				  "descricaoResumida":"tst produto",
				  "fator":1.0,
				  "id":2,
				  "unidade":"und",
				  "valorUnitario":175.95
				}
		,
				"qtd":3.0,
				"sequencia":1,
				"total":3.00,
				"valorUnitario":1.0
			  }
		,
			  
			  {
				"desconto":0.5,
				"idpedido":3,
				"idpedidoitem":4,
				"idproduto":3,
				"produto":
				{
				  "codBarras":"54321",
				  "descricaoCompleta":"Entrega trabalho final",
				  "descricaoResumida":"Ent Trb final",
				  "fator":12.0,
				  "id":3,
				  "unidade":"und",
				  "valorUnitario":1200.0
				}
		,
				"qtd":4.0,
				"sequencia":1,
				"total":4.00,
				"valorUnitario":1.0
			  }
			]
		,
			"qtdItens":2,
			"subTotal":7.00,
			"total":6.00
		  }
		]
		
		
		
	* POST http://{servidor}:{porta}/PedidoOnlineWs/api/pedidos
	deverá ser enviado um body com os dados do pedido a ser incluido
	Body Exemplo: 
		
		{
		  "clienteMesaComanda":"",
		  "dataCriacao":"2021-05-29T00:00:00-03:00",
		  "desconto":1.00,		 
		  "itens":
		  [
				{
			  "desconto":0.5,
			  "idproduto":2,			 
			  "qtd":3.0,
			  "sequencia":1,
			  "total":3.00,
			  "valorUnitario":1.0
			}
		,
			
			{
			  "desconto":0.5,
			  "idproduto":3,			  
			  "qtd":4.0,
			  "sequencia":1,
			  "total":4.00,
			  "valorUnitario":1.0
			}
		  ]
		,
		  "qtdItens":2,
		  "subTotal":7.00,
		  "total":6.00
		}

		
		
		
   * GET http://{servidor}:{porta}/PedidoOnlineWs/api/pedidos/{idPedido}
   - Exemplo Retorno, content:
		
		{
		  "clienteMesaComanda":"",
		  "dataCriacao":"2021-05-29T00:00:00-03:00",
		  "desconto":1.00,
		  "idpedido":4,
		  "itens":
		  [
				{
			  "desconto":0.5,
			  "idpedido":4,
			  "idpedidoitem":5,
			  "idproduto":2,
			  "produto":
			  {
				"codBarras":"14632",
				"descricaoCompleta":"Teste produto novo",
				"descricaoResumida":"tst produto",
				"fator":1.0,
				"id":2,
				"unidade":"und",
				"valorUnitario":175.95
			  }
		,
			  "qtd":3.0,
			  "sequencia":1,
			  "total":3.00,
			  "valorUnitario":1.0
			}
		,
			
			{
			  "desconto":0.5,
			  "idpedido":4,
			  "idpedidoitem":6,
			  "idproduto":3,
			  "produto":
			  {
				"codBarras":"54321",
				"descricaoCompleta":"Entrega trabalho final",
				"descricaoResumida":"Ent Trb final",
				"fator":12.0,
				"id":3,
				"unidade":"und",
				"valorUnitario":1200.0
			  }
		,
			  "qtd":4.0,
			  "sequencia":1,
			  "total":4.00,
			  "valorUnitario":1.0
			}
		  ]
		,
		  "qtdItens":2,
		  "subTotal":7.00,
		  "total":6.00
		}

		
		
		
   * PUT http://{servidor}:{porta}/PedidoOnlineWs/api/pedidos/{idPedido}
	deverá ser enviado um body com os dados do pedido a ser alterado
	caso queira alterar um item do pedido, madar o idpedidoitem no array de itens
   - Body Exemplo: 
		
		{
		  "clienteMesaComanda":"",
		  "dataCriacao":"2021-05-29T00:00:00-03:00",
		  "desconto":1.00,		 
		  "itens":
		  [
				{
			  "desconto":0.5,			 
			  "idpedidoitem":5,
			  "idproduto":2,
			  "produto":
			  {
				"codBarras":"14632",
				"descricaoCompleta":"Teste produto novo",
				"descricaoResumida":"tst produto",
				"fator":1.0,
				"id":2,
				"unidade":"und",
				"valorUnitario":175.95
			  }
		,
			  "qtd":3.0,
			  "sequencia":1,
			  "total":3.00,
			  "valorUnitario":1.0
			}
		,
			
			{
			  "desconto":0.5,			  
			  "idpedidoitem":6,
			  "idproduto":3,
			  "produto":
			  {
				"codBarras":"54321",
				"descricaoCompleta":"Entrega trabalho final",
				"descricaoResumida":"Ent Trb final",
				"fator":12.0,
				"id":3,
				"unidade":"und",
				"valorUnitario":1200.0
			  }
		,
			  "qtd":4.0,
			  "sequencia":1,
			  "total":4.00,
			  "valorUnitario":1.0
			}
		  ]
		,
		  "qtdItens":2,
		  "subTotal":7.00,
		  "total":6.00
		}

		
	Em caso de sucesso será retornado o responseCode 204

	

	* DELETE http://{servidor}:{porta}/PedidoOnlineWs/api/pedidos/{idPedido}
	  Em caso de sucesso será retornado o responseCode 204





































