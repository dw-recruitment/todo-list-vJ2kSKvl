(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.8.0"]
                  [ring/ring-core "1.4.0"]
                  [ring/ring-jetty-adapter "1.4.0"]
                  [ring/ring-defaults "0.2.0"]
                  [compojure "1.5.0"]
                  [environ "1.0.2"]
                  [org.clojure/java.jdbc "0.3.7"]
                  [org.postgresql/postgresql "9.4.1208.jre7"]
                  [java-jdbc/dsl "0.1.3"]
                  [korma "0.4.2"]
                  [hiccup "1.0.4"]
                  ]
  :main todo.core
  :plugins [[lein-environ "1.0.2"]]
  :profiles {
    :dev {
      :env {
        :port "3000"
        }
      }
    }
  )