package it.ninebee.lasa.laisfala.connector;

public interface IConnectorAttributeParser<A> {

	public A parse(String source);
	public A parse(ConnectorVO source);
	
}
