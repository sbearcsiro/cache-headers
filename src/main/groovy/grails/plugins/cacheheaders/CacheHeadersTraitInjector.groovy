package grails.plugins.cacheheaders

import grails.compiler.traits.*

import groovy.transform.CompileStatic


@CompileStatic
class CacheHeadersTraitInjector implements TraitInjector {
    
    @Override
    Class getTrait() {
        CacheHeadersTrait
    }

    @Override
    String[] getArtefactTypes() {
        ['Controller'] as String[]
    }
}