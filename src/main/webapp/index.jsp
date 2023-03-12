<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<h1>Formulario Banco</h1>
<div>
    <form>
        <div>
            <h1>Crear cuenta</h1>
            <label>Nombre Cliente</label>
            <input type="text" id="inputNombreCliente" name="inputNombreCliente">
        </div>
        <div>
            <label>Id Fiscal (DNI,NIE)</label>
            <input type="text" id="inputIdFiscal" name="inputIdFiscal">
        </div>
        <div>
            <label>Email Cliente</label>
            <input type="text" id="inputEmail" name="inputEmail">
        </div>
        <div>
            <label>Pais</label>
            <input type="text" id="inputPais" name="inputPais">
        </div>
        <div>
            <label>Cuenta</label>
            <input type="text" id="inputCuenta" name="inputCuenta">
        </div>
        <div>
            <label for="inputIngresoInicial" class="form-label">Ingreso inicial (€)</label>
            <input type="text" id="inputIngresoInicial" name="inputIngresoInicial">
        </div>
        <div>
            <button type="submit">Crear Cuenta</button>
        </div>
    </form>
</div>
<br></br>
<div>
    <h1>Buscar Cliente</h1>
    <form action="showClients" method="post">
        <div>
            <label>DNI Cliente</label>
            <input type="text" id="dniBuscar" name="dniBuscar">
        </div>
        <div>
            <button>Mostrar cliente y sus cuentas</button>
        </div>
    </form>
</div>
<div>
    <h1>Buscar Cuenta</h1>
    <form action="ShowAcc" method="post">
        <div>
            <label>IBAN Cuenta</label>
            <input type="text" id="ibanBuscar" name="ibanBuscar">>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Mostrar cuenta y su Cliente</button>
        </div>
    </form>
</div>
</body>
</html>