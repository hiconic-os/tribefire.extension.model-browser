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
package tribefire.extension.model_browser.module.wire.space;

import com.braintribe.model.processing.deployment.api.ExpertContext;
import com.braintribe.model.processing.deployment.api.binding.DenotationBindingBuilder;
import com.braintribe.wire.api.annotation.Import;
import com.braintribe.wire.api.annotation.Managed;

import tribefire.extension.model_browser.module.initializer.ModelBrowserInitializer;
import tribefire.extension.modelbrowser.servelet.ModelBrowser;
import tribefire.module.api.InitializerBindingBuilder;
import tribefire.module.wire.contract.ModuleResourcesContract;
import tribefire.module.wire.contract.TribefireModuleContract;
import tribefire.module.wire.contract.TribefireWebPlatformContract;

/**
 * The ModelBrowser module creates the ModelBrowser denotation types as well as the corresponding expert.
 * 
 * @author Dirk Scheffler
 */
@Managed
public class ModelBrowserModuleSpace implements TribefireModuleContract {

	@Import
	private TribefireWebPlatformContract tfPlatform;

	@Import
	private ModuleResourcesContract moduleResources;

	//
	// Initializers
	//

	@Override
	public void bindInitializers(InitializerBindingBuilder bindings) {
		bindings.bind(modelBrowserInitializer());
	}

	//
	// Deployables
	//

	@Override
	public void bindDeployables(DenotationBindingBuilder bindings) {
		bindings.bind(tribefire.extension.modelbrowser.model.deployment.ModelBrowser.T).component(tfPlatform.binders().webTerminal())
				.expertFactory(this::modelBrowser);
	}

	//
	// Experts
	//

	@Managed
	private ModelBrowserInitializer modelBrowserInitializer() {
		return new ModelBrowserInitializer();
	}

	@Managed
	private ModelBrowser modelBrowser(ExpertContext<tribefire.extension.modelbrowser.model.deployment.ModelBrowser> context) {
		tribefire.extension.modelbrowser.model.deployment.ModelBrowser deployable = context.getDeployable();
		ModelBrowser bean = new ModelBrowser();
		bean.setCortexSessionProvider(tfPlatform.systemUserRelated().cortexSessionSupplier());
		bean.setResourceBaseUrl(moduleResources.resource(".").asUrl());
		bean.setModelAccessoryFactory(tfPlatform.systemUserRelated().modelAccessoryFactory());
		bean.setRequiredRoles(deployable.getRequiredRoles());
		bean.setUserSessionStack(tfPlatform.requestUserRelated().userSessionStack());
		return bean;
	}
}
