Projeto : Edglê Churrascaria

Para informações sobre documentação procure o proprietario

Para executar o projeto: 
Usar server Tomcat 9.0
Criar bd postgres com nome : EdgleChurrascaria
adicionar em %CATALINA_HOME%/conf/server.xml :
 	Em GlobalNamingResources:
	<Resource name="jdbc/edgleChurrascaria" auth="Container"
			type="javax.sql.DataSource" maxTotal="100" maxIdle="30"
			maxWaitMillis="10000" username="postgres" password="1234"
			driverClassName="org.postgresql.Driver"
			url="jdbc:postgresql://localhost:5432/EdgleChurrascaria" />
	adicionar novo realm:
	<Realm className="org.apache.catalina.realm.DataSourceRealm"
			dataSourceName="jdbc/edgleChurrascaria" userTable="TB_Funcionarios"
			userNameCol="login" userCredCol="senha" userRoleTable="TB_Funcionarios"
			roleNameCol="tipodefuncionario" />