package grails.plugins.cacheheaders

import grails.plugins.*
import groovy.util.logging.*
import groovy.transform.*
import grails.core.*

@Commons
class CacheHeadersGrailsPlugin extends Plugin {
	def grailsVersion = "3.0 > *"
	def observe = ['controllers']
	def pluginExcludes = [
		"**/TestController**",
		"src/docs/**"
	]
	def author = "Graeme Rocher"
	def authorEmail = "grocher@gopivotal.com"
	def title = "Caching Headers Plugin"
	def description = 'Improve your application performance with browser caching, with easy ways to set caching headers in controller responses'
	def developers = [ [ name: "Marc Palmer", email: "marc@grailsrocks.com" ], [ name: "Graeme Rocher", email: "grocher@gopivotal.com" ]]
	def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPCACHEHEADERS" ]
	def scm = [ url: "http://github.com/grails-plugins/grails-cache-headers" ]
	def license = "APACHE"
	def documentation = "http://grails.org/plugin/cache-headers"


	void doWithApplicationContext() {
		reloadConfig(grailsApplication, applicationContext.cacheHeadersService)
	}

	void onConfigChange(Map<String, Object> event) {
		// Config change might mean that the caching has been turned on/off
		reloadConfig(event.application, event.application.mainContext.cacheHeadersService)
	}

	private void reloadConfig(application, svc) {
		def conf = application.config.cache.headers
		def cacheSetting = conf.enabled
		svc.enabled = ((cacheSetting instanceof String) || (cacheSetting instanceof Boolean)) ? Boolean.valueOf(cacheSetting.toString()) : true
		svc.presets = conf.presets
		log.info "Caching enabled in Config: ${svc.enabled}"
		log.debug "Caching presets declared: ${svc.presets}"
	}

}
