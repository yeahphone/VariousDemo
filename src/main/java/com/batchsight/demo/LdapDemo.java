package com.batchsight.demo;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.*;
import org.junit.*;

import java.io.IOException;

class LdapDemo {
  @Test
  void testLdapConnection() throws LdapException, IOException {
    LdapConnectionConfig config = new LdapConnectionConfig();
    config.setLdapHost("node02.cloud.batchsight.com");
    config.setLdapPort(389);
    config.setName("uid=admin,ou=system");
    config.setCredentials("secret");
    LdapConnectionPool connectionPool = new LdapConnectionPool(new DefaultPoolableLdapConnectionFactory(config));

    LdapConnection ldapConnection = connectionPool.getConnection();

    Dn baseDn = new Dn("ou=warehouse,ou=制剂二厂,o=恒瑞医药,dc=ibatchpharma,dc=batchsight,dc=com");

    final EntryCursor entryCursor = ldapConnection.search(
        //"ou=warehouse,ou=制剂二厂,o=恒瑞医药,dc=ibatchpharma,dc=batchsight,dc=com",
        baseDn,
        "(&(objectClass=domain-object)(objectClass=location)(cn=*车间*))",
        SearchScope.ONELEVEL,
        "*");

    for (final Entry entry : entryCursor) {

      System.out.println(entry.getDn());

      Dn parent = entry.getDn().getParent();

      while (!parent.equals(baseDn)) {
        System.out.println(parent.getRdn());
        parent = parent.getParent();
      }
    }

    connectionPool.releaseConnection(ldapConnection);
  }
}
