/*******************************************************************************
 * Copyright (c) 2013 <Project SWG>
 * 
 * This File is part of NGECore2.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Using NGEngine to work with NGECore2 is making a combined work based on NGEngine. 
 * Therefore all terms and conditions of the GNU Lesser General Public License cover the combination.
 ******************************************************************************/
package resources.gcw;

import java.nio.ByteOrder;

import org.apache.mina.core.buffer.IoBuffer;

import com.sleepycat.persist.model.Persistent;

import resources.objects.ListObject;

@Persistent
public class OtherServerGCWZonePercent extends ListObject implements Cloneable {
	
	private String zone = "";
	private int percent = 50;

	public OtherServerGCWZonePercent(String zone) {
		this.zone = zone;
	}
	
	public String getZone() {
		synchronized(objectMutex) {
			return zone;
		}
	}
	
	public int getPercent() {
		synchronized(objectMutex) {
			return percent;
		}
	}
	
	public OtherServerGCWZonePercent setPercent(double percent) {
		synchronized(objectMutex) {
			this.percent = (int) percent;
			return this;
		}
	}
	
	public byte[] getBytes() {
		synchronized(objectMutex) {
			IoBuffer buffer = bufferPool.allocate((2 + zone.length() + 4), false).order(ByteOrder.LITTLE_ENDIAN);
			buffer.put(getAsciiString(zone));
			buffer.putInt(percent);
			return buffer.array();
		}
	}
	
	@Override
	public OtherServerGCWZonePercent clone() {
		try {
			return (OtherServerGCWZonePercent) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
