package org.mongeez.dao;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongeez.MongoAuth;
import com.github.nlloyd.hornofmongo.MongoRuntime;
import com.github.nlloyd.hornofmongo.MongoScope;
import com.github.nlloyd.hornofmongo.action.MongoScriptAction;

import java.net.UnknownHostException;

/**
 * Created by tash on 6/9/14.
 */
public class RawMongeezDao extends MongeezDao {

    private MongoScope myMongoScope;

    public RawMongeezDao(Mongo mongo, String databaseName) {
        super(mongo, databaseName);
    }

    public RawMongeezDao(Mongo mongo, String databaseName, MongoAuth auth) {
        super(mongo, databaseName, auth);
    }

    public RawMongeezDao(MongoClientURI mongo, String databaseName, MongoAuth auth) throws UnknownHostException {
        super(new MongoClient(mongo), databaseName, auth);
        myMongoScope = MongoRuntime.createMongoScope(mongo, true, true);
        MongoRuntime.call(new MongoScriptAction(myMongoScope, "db.auth('"+auth.getUsername()+ "','"+auth.getPassword()+"')"));
    }

    public void runScript(String code) {
        MongoRuntime.call(new MongoScriptAction(myMongoScope, code));
    }
}
