// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package tribefire.extension.modelbrowser.model.deployment;

import java.util.Set;

import com.braintribe.model.extensiondeployment.AuthorizedWebTerminal;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * The Model Browser denotation type, deriving from {@link AuthorizedWebTerminal}.
 * 
 */
public interface ModelBrowser extends AuthorizedWebTerminal {

	EntityType<ModelBrowser> T = EntityTypes.T(ModelBrowser.class);

	Set<String> getRequiredRoles();
	void setRequiredRoles(Set<String> requiredRoles);
}