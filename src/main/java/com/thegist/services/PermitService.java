package com.thegist.services;

import com.thegist.domain.Application;

public interface PermitService {


	public Application issue(Application Application);

	public Application reject(Application Application);

	public Application requestMoreInformation(Application Application);

	public Application revoke(Application Application);

	public Application revise(Application Application);

}
	

