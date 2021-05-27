-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.45-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para pedidos_online
CREATE DATABASE IF NOT EXISTS `pedidos_online` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pedidos_online`;

-- Copiando estrutura para tabela pedidos_online.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT COMMENT 'chave primária do pedido',
  `data_criacao` datetime DEFAULT NULL COMMENT 'data de criação do pedido',
  `sub_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'valor bruto dos itens',
  `desconto` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'desconto total aplicado',
  `total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'valor total do pedido sem o desconto',
  `qtd_itens` int(11) NOT NULL DEFAULT '0' COMMENT 'quantidade de itens que o pedido possui',
  `cliente_mesa_comanda` varchar(14) NOT NULL DEFAULT '' COMMENT 'cpf / cnpj / mesa ou comanda onde o pedido foi realizado',
  PRIMARY KEY (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela pedidos_online.pedido_item
CREATE TABLE IF NOT EXISTS `pedido_item` (
  `idPedidoItem` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id do Item pedido',
  `idPedido` int(11) NOT NULL COMMENT 'id do pedido',
  `idProduto` int(11) NOT NULL COMMENT 'id do produto',
  `qtd` double(10,2) NOT NULL DEFAULT '0.00' COMMENT 'quantidade pedido',
  `valor_unitario` double(10,2) NOT NULL DEFAULT '0.00' COMMENT 'valor atual do produto',
  `desconto` double(10,2) NOT NULL DEFAULT '0.00' COMMENT 'desconto aplicado em valor',
  `total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'total sem o desconto',
  `sequencia` int(11) NOT NULL DEFAULT '1' COMMENT 'sequencia iniciando em 1',
  PRIMARY KEY (`idPedidoItem`),
  KEY `fk_pedido_idPedido` (`idPedido`),
  KEY `fk_produto_idProduto` (`idProduto`),
  CONSTRAINT `fk_pedido_idPedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_idProduto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela pedidos_online.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id do produto',
  `descricao_completa` varchar(80) NOT NULL DEFAULT '' COMMENT 'descrição completa do produto',
  `descricao_resumida` varchar(40) NOT NULL DEFAULT '' COMMENT 'descrição resumida para visualizar no cupom',
  `unidade` varchar(3) NOT NULL DEFAULT '' COMMENT 'unidade medido (UND) (KG)',
  `fator` double NOT NULL DEFAULT '1' COMMENT 'Fator de conversão caso tenha',
  `valor_unitario` double NOT NULL DEFAULT '0' COMMENT 'valor unitário do produto',
  `cod_barras` varchar(20) NOT NULL DEFAULT '' COMMENT 'Codigo de barras para leitura',
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
