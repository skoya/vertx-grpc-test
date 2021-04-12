package to.du.api;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;

import java.io.IOException;

public class main {

    public static void main(String[] args) {
        Vertx v = Vertx.vertx();
        to.du.api.VertxDusGrpc.DusVertxImplBase service = new to.du.api.VertxDusGrpc.DusVertxImplBase() {
            @Override
            public Future<to.du.api.DuProto.DuReply> getDues(to.du.api.DuProto.DuRequest request) {
                return Future.succeededFuture(to.du.api.DuProto.DuReply.newBuilder().build());
            }
        };

        VertxServer rpcServer = VertxServerBuilder
                .forAddress(v, "0.0.0.0", 8080)
                .addService(service)
                .build();
        try {
            rpcServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
