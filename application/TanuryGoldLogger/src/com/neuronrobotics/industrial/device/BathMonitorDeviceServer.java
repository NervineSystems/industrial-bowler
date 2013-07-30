package com.neuronrobotics.industrial.device;

import java.util.List;

import com.neuronrobotics.sdk.common.BowlerAbstractConnection;
import com.neuronrobotics.sdk.common.BowlerDatagram;
import com.neuronrobotics.sdk.common.device.server.BowlerAbstractDeviceServerNamespace;
import com.neuronrobotics.sdk.dyio.DyIO;
import com.neuronrobotics.sdk.network.AbstractNetworkDeviceServer;
import com.neuronrobotics.sdk.network.BowlerUDPServer;
import com.neuronrobotics.sdk.serial.SerialConnection;

public class BathMonitorDeviceServer extends AbstractNetworkDeviceServer{
	


	public BathMonitorDeviceServer(BowlerAbstractConnection device) {
		super(device,false,new BowlerUDPServer(1865));
		addBowlerDeviceServerNamespace(new BowlerAbstractDeviceServerNamespace() {
			
			@Override
			public BowlerDatagram process(BowlerDatagram data) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialConnection con=null;
		if(args.length != 1){
			System.err.println("No port specified, choosing first");
			
			List<String> ports = SerialConnection.getAvailableSerialPorts();
			for(String s:ports){
				System.err.println(s);
			}
			if(ports.size() >0){
				con = new SerialConnection(ports.get(0));
			}else{
				System.err.println("No port availible");
				System.exit(1);
			}
		}else{
			System.out.println("Using port: "+args[0]);
			con  = new SerialConnection(args[0]);
		}
		DyIO.disableFWCheck();
		DyIO dyio = new DyIO(con);
		dyio.connect();
		new BathMonitorDeviceServer(dyio.getConnection());
	}
}
