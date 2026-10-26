//package com.umar.grpc;
//
//import com.umar.entity.Satellite;
//import com.umar.enums.missionRegistry.OrbitType;
//import com.umar.enums.missionRegistry.Status;
//import com.umar.repository.SatelliteRepository;
//import io.grpc.stub.StreamObserver;
//import lombok.RequiredArgsConstructor;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//import java.util.UUID;
//
//@GrpcService
//@RequiredArgsConstructor
//public class MissionRegistryGrpcService extends RegistryServiceGrpc.RegistryServiceImplBase {
//
//    private final SatelliteRepository satelliteRepository;
//
//    @Override
//    public void getSatellite(RegistryProto.GetSatelliteRequest request,
//                            StreamObserver<RegistryProto.GetSatelliteResponse> responseObserver) {
//        try {
//            UUID satelliteId = UUID.fromString(request.getSatelliteId());
//            Satellite satellite = satelliteRepository.findById(satelliteId)
//                    .orElseThrow(() -> new RuntimeException("Satellite not found"));
//
//            RegistryProto.GetSatelliteResponse response = RegistryProto.GetSatelliteResponse.newBuilder()
//                    .setSatelliteId(satellite.getId().toString())
//                    .setNoradId(satellite.getNoradId())
//                    .setMissionId(satellite.getMission() != null ? satellite.getMission().getId().toString() : "")
//                    .setOrbitType(satellite.getOrbitType().name())
//                    .setStatus(satellite.getStatus().name())
//                    .build();
//
//            responseObserver.onNext(response);
//            responseObserver.onCompleted();
//        } catch (Exception e) {
//            responseObserver.onError(io.grpc.Status.NOT_FOUND
//                    .withDescription(e.getMessage())
//                    .asRuntimeException());
//        }
//    }
//
//    @Override
//    public void listActiveSatellites(RegistryProto.ListActiveSatellitesRequest request,
//                                      StreamObserver<RegistryProto.SatelliteData> responseObserver) {
//        try {
//            OrbitType orbitTypeFilter = null;
//            if (!request.getOrbitTypeFilter().isEmpty()) {
//                orbitTypeFilter = OrbitType.valueOf(request.getOrbitTypeFilter().toUpperCase());
//            }
//
//            satelliteRepository.findByStatusAndOrbitType(Status.ACTIVE, orbitTypeFilter, null)
//                    .forEach(satellite -> {
//                        RegistryProto.SatelliteData data = RegistryProto.SatelliteData.newBuilder()
//                                .setSatelliteId(satellite.getId().toString())
//                                .setNoradId(satellite.getNoradId())
//                                .setMissionId(satellite.getMission() != null ? satellite.getMission().getId().toString() : "")
//                                .setOrbitType(satellite.getOrbitType().name())
//                                .build();
//                        responseObserver.onNext(data);
//                    });
//
//            responseObserver.onCompleted();
//        } catch (Exception e) {
//            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
//                    .withDescription(e.getMessage())
//                    .asRuntimeException());
//        }
//    }
//}
