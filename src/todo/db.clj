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

(def select-todo-items
  ;(map vec (korma-core/select todolist))
  ;(korma-core/exec-raw ["SELECT * FROM todolist"])
  (korma-core/select todolist)
  )

(defn insert-item [task]
  (let [qry (str "INSERT INTO todolist (task, todo, done) VALUES ('" task "',true,false);")]
    (korma-core/exec-raw [qry])
    )
  )