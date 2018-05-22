package com.ceiba.parking.application.service;

import java.rmi.RemoteException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ceiba.parking.application.domain.TcrmDTO;

import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;


@Service
public class TcrmController{	
	public TcrmDTO consultaTrm() throws RemoteException {
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy("https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL");
		TcrmResponse response = proxy.queryTCRM(null);
		return new TcrmDTO(response.getUnit(),response.getValue(),new Date(), response.getSuccess());
	}
}
