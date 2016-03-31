(ns todo.db
  (:require [korma.db :as korma]
            [korma.core :as korma-core]
    )
  )

(declare todolist)

(def db (korma/postgres {
                      :db "todo"
                      :user "dev"
                      :password "dev"
                      }
            )
  )

(korma/defdb korma-db db)

(korma-core/defentity todolist
  (korma-core/entity-fields :task :done))

(def select-todo-items (map vec (korma-core/select todolist
    ))
  )