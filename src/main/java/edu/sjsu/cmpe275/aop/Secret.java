package edu.sjsu.cmpe275.aop;

import java.util.Date;
import java.util.UUID;

/**
 * @author charleszhang
 * Please note this file is NOT part of your submission.
 *
 */
public class Secret {
	
	private final UUID id;
	private final String content;
	private final Date creationTime;

	public Secret(String content) {
		super();
		this.content = content;
		creationTime = new Date();
		id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public String toString() {
		return "Secret [id=" + id + ", content=" + content + ", creationTime=" + creationTime + "]";
	}
}
