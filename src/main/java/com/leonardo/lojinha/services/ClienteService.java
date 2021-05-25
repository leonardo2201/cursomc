package com.leonardo.lojinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonardo.lojinha.dto.ClienteDTO;
import com.leonardo.lojinha.dto.ClienteNewDTO;
import com.leonardo.lojinha.entity.Cidade;
import com.leonardo.lojinha.entity.Cliente;
import com.leonardo.lojinha.entity.Endereco;
import com.leonardo.lojinha.entity.enums.TipoCliente;
import com.leonardo.lojinha.repositories.ClienteDAO;
import com.leonardo.lojinha.repositories.EnderecoDAO;
import com.leonardo.lojinha.services.exceptions.DataIntegrityException;
import com.leonardo.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private EnderecoDAO enderecoDAO;

	public Cliente findById(Integer id) {
		Optional<Cliente> clienteOpt = clienteDAO.findById(id);
		Cliente cliente = null;
		
		if(clienteOpt.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		
		cliente = clienteOpt.get();
		return cliente;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteDAO.save(obj);
		enderecoDAO.saveAll(obj.getEndereco());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clienteDAO.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			clienteDAO.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir cliente com pedidos");
		}
	}

	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteDAO.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEndereco().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
}
