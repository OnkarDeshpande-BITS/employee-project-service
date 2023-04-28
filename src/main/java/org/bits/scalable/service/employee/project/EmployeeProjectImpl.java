package org.bits.scalable.service.employee.project;

import io.grpc.stub.StreamObserver;
import org.bits.scalable.service.employee.grpc.EmployeeProjectGrpc;
import org.bits.scalable.service.employee.grpc.GetProjectDetailsRequest;
import org.bits.scalable.service.employee.grpc.GetProjectDetailsResponse;
import org.bits.scalable.service.employee.grpc.ProjectDetailsResult;

import java.util.logging.Logger;

public class EmployeeProjectImpl extends EmployeeProjectGrpc.EmployeeProjectImplBase {

    private static final Logger logger = Logger.getLogger(EmployeeProjectImpl.class.getName());
    @Override
    public void getProjectDetails(GetProjectDetailsRequest request,
                                  StreamObserver<GetProjectDetailsResponse> responseObserver) {
        String empId = request.getEmpId();
        logger.info("Getting project details for employee -"+empId);
        //MockResponse
        ProjectDetailsResult result = ProjectDetailsResult.newBuilder()
                .setEmpName("Emp1").setDepartment("RnD")
                .addProjects("Scalable Service Assignment")
                .addProjects("Microservice design").build();
        responseObserver.onNext(GetProjectDetailsResponse.newBuilder().setResutl(result).build());
        responseObserver.onCompleted();

    }

}
