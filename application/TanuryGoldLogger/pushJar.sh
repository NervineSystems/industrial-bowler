
for VAR in "$@"
do
	echo Found $VAR
    #if  [ $(ssh pi@$VAR 'mkdir .ssh') ];  then
	#	echo Uploading keys $VAR;
	#	$(scp ~/.ssh/id_dsa.pub pi@$VAR:.ssh/authorized_keys2);	
	#fi;
	#scp ~/.ssh/id_dsa.pub pi@$VAR:.ssh/authorized_keys2
	#scp setupRoot.sh pi@$VAR: 
	
	ssh tanury@$VAR 'mkdir -p Tanury/device'
	
	#ssh root@$VAR 'mount -o remount,rw /'
	
	scp target/TanuryDeviceServer.jar root@$VAR:/

	scp 80-neuronrobotics.rules root@$VAR:/etc/udev/rules.d/
	scp tanuryCron.sh root@$VAR:/
	scp tanuryCronStatus.sh root@$VAR:/
	
	scp rcS root@$VAR:/etc/default/
	
	scp grub root@$VAR:/etc/default/
	ssh root@$VAR 'touch /boot/grub/menu.lst'
	ssh root@$VAR 'update-grub'
	
	#ssh root@$VAR 'ntptime;rpi-update'
	#ssh root@$VAR 'mount -o remount,ro /'

	#ssh root@$VAR 'fsck -y /dev/mmcblk0p2'
	
	ssh root@$VAR 'pkill java'
done

