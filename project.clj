(defproject umlaut "0.1.9-SNAPSHOT"
  :description "A Clojure program that receives a schema and outputs code."
  :url "https://github.com/workco/umlaut"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clojure-future-spec "1.9.0-alpha14"]
                 [instaparse "1.4.5"]]
  :plugins [[lein-umlaut "0.1.0-SNAPSHOT"]]
  :main umlaut.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {
                   :dependencies
                   [[org.clojure/test.check "0.9.0"]]}})
