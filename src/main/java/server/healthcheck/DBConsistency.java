package server.healthcheck;

import com.codahale.metrics.health.HealthCheck;

import api.resource.ListEntriesResource;
import api.resource.ListIdsResource;

public class DBConsistency extends HealthCheck
{
	@Override
	protected Result check() throws Exception
	{
		ListEntriesResource entries = new ListEntriesResource();
		ListIdsResource ids = new ListIdsResource();
		if(entries.listEntries().getEntries().size() != ids.listIds().getIds().size()){
			Result.unhealthy("Number of ids and movies unmatched...");
		}
		return Result.healthy();
	}
}
