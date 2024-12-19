package org.example.library.bus;

import org.example.library.algorithm.GenericAlgorithm;
import org.example.library.daos.ReaderDao;
import org.example.library.models.Reader;

import java.util.List;
import java.util.UUID;

public class ReaderBus {
    private final ReaderDao readerDao;

    public ReaderBus() {
        this.readerDao = new ReaderDao();
    }

    public List<Reader> getAllReaders() {
        return readerDao.getReaders();
    }

    public String generateReaderId() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public void saveInfo(Reader reader) {
        Reader existingReader = readerDao.findById(reader.getReaderId());

        if (existingReader == null) {
            if (readerDao.isPhoneNumberExists(reader.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number already exists!");
            }
        } else {
            if (!existingReader.getPhoneNumber().equals(reader.getPhoneNumber()) &&
                    readerDao.isPhoneNumberExists(reader.getPhoneNumber())) {
                throw new IllegalArgumentException("Phone number already exists!");
            }
        }

        readerDao.save(reader);
    }


    public void deleteReader(String readerId) {
        readerDao.delete(readerId);
    }

    public List<Reader> search(String keyword) {
        return GenericAlgorithm.search(getAllReaders(), keyword, reader ->
                reader.getReaderName() + " " +
                        reader.getReaderId() + " " +
                        reader.getReaderAddress() + " " +
                        reader.getPhoneNumber() + " " +
                        reader.getDob());
    }

    public List<String> getReaderIds() {
        return getAllReaders().stream().map(Reader::getReaderId).toList();
    }

    public Reader findReaderBy(String id) {
        return readerDao.findById(id);
    }
}
