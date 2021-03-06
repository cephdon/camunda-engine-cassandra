/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.cassandra.provider.indexes;

import org.camunda.bpm.engine.impl.persistence.entity.EventSubscriptionEntity;

/**
 * @author Natalia Levine
 *
 * @created 12/07/2015
 */
public class ExecutionIdByEventTypeAndNameIndex extends AbstractIndexHandler<EventSubscriptionEntity> {

  @Override
  protected String getIndexName() {
     return IndexNames.EXECUTION_ID_BY_EVENT_NAME;
  }

  @Override
  public boolean isUnique() {
    return false;
  }

  @Override
  protected String getIndexValue(String... indexValues) {
    if(indexValues.length!=2){
      throw new IllegalArgumentException("ExecutionIdByEventTypeAndNameIndex requires event type and event name");
    }
    
    return IndexUtils.createIndexValue(indexValues);
  }

  @Override
  protected String getIndexValue(EventSubscriptionEntity entity) {
    return IndexUtils.createIndexValue(entity.getEventType(), entity.getEventName());
  }

  @Override
  protected String getValue(EventSubscriptionEntity entity) {
    return entity.getExecutionId();
  }

}
