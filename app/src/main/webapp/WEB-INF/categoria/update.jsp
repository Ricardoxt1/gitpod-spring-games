<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
   <head>
      <meta charset="utf-8">
      <title>Editar Categoria</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
   </head>
   <body>
      <div class="container">
         <h1>Editar Categoria</h1>
         <form action="/categoria/update" method="post">
            <div class="form-group">
               <input type="hidden" name="id" value="${categoria.id}" />
               <label for="nome">Nome: </label>
               <input type="text" name="nome" id="nome" value="${categoria.nome}" class="form-control" />
            </div>
           <br/>
           <a href="/categoria/list" class="btn btn-primary">Voltar</a>
           <button type="submit" class="btn btn-success">Salvar</button>
         </form>
      </div>
   </body>
</html>