package com.twitter.finatra.http.internal.marshalling.mustache

import com.twitter.finatra.http.marshalling.mustache.MustacheBodyComponent
import com.twitter.finatra.response.Mustache
import com.twitter.inject.conversions.map._
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton

@Singleton
private[finatra] class MustacheTemplateNameLookup {

  private val classToTemplateNameCache = new ConcurrentHashMap[Class[_], String]()

  /* Public */

  def getTemplateName(obj: Any): String = {
    obj match {
      case mbc: MustacheBodyComponent if !mbc.template.isEmpty => mbc.template
      case mbc: MustacheBodyComponent => lookupViaAnnotation(mbc.data)
      case _ => lookupViaAnnotation(obj)
    }
  }

  /* Private */

  private def lookupViaAnnotation(viewObj: Any): String = {
    classToTemplateNameCache.atomicGetOrElseUpdate(viewObj.getClass, {
      val mustacheAnnotation = viewObj.getClass.getAnnotation(classOf[Mustache])
      mustacheAnnotation.value + ".mustache"
    })
  }

}
