package hmi.joboffersearchengine.config;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.CommonFunctionFactory;
//import org.hibernate.dialect.function.SqlFunctionTemplate;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.query.spi.QueryEngine;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Types;

public class SQLiteDialect extends Dialect{

    public SQLiteDialect() {
        super(DatabaseVersion.make(3, 36));


    }

}
