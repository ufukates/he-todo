package com.hepsiemlak.todo.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.bucket.BucketSettings;
import com.hepsiemlak.todo.TodoApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;

@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestcontainerBase {

    public static final CouchbaseContainer couchbaseContainer;
    public static final Cluster cluster;

    static {
        couchbaseContainer = new CouchbaseContainer("couchbase/server")
                .withCredentials("testBucket", "testPassword")
                .withExposedPorts(8091, 8092, 8093, 8094, 11207, 11210, 11211, 18091, 18092, 18093)
                .withBucket(new BucketDefinition("testBucket"));



        couchbaseContainer.start();

        cluster = Cluster.connect(
                couchbaseContainer.getConnectionString(),
                couchbaseContainer.getUsername(),
                couchbaseContainer.getPassword()
        );

        System.setProperty("spring.couchbase.connection-string", couchbaseContainer.getConnectionString());
        System.setProperty("spring.data.couchbase.bucket-name", "testBucket");
        System.setProperty("spring.couchbase.username", couchbaseContainer.getUsername());
        System.setProperty("spring.couchbase.password", couchbaseContainer.getPassword());
    }

}
