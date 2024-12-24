package org.example.library.daos;

import org.example.library.algorithm.GenericAlgorithm;
import org.example.library.common.FilePath;
import org.example.library.models.Reader;
import org.example.library.utility.FileUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReaderDao {
    private final List<Reader> readers;

    public ReaderDao() {
        this.readers = FileUtil.readFromFile(FilePath.READER_FILE, line -> {
            String[] parts = line.split(",");
            return new Reader(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        });
    }

    public List<Reader> getReaders() {
        readers.clear();
        readers.addAll(FileUtil.readFromFile(FilePath.READER_FILE, line -> {
            String[] parts = line.split(",");
            return new Reader(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }));

        return readers;
    }

    public Reader findById(String id) {
        List<Reader> sortedReaders = new ArrayList<>(readers);
        //  sortedReaders.sort(Comparator.comparing(Reader::getReaderId));

        List<Reader> result = GenericAlgorithm.search(sortedReaders, id, Reader::getReaderId);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Reader reader) {
        Reader existingReader = findById(reader.getReaderId());
        if (existingReader != null) {
            existingReader.setReaderName(reader.getReaderName());
            existingReader.setReaderAddress(reader.getReaderAddress());
            existingReader.setPhoneNumber(reader.getPhoneNumber());
            existingReader.setDob(reader.getDob());

            readers.removeIf(r -> r.getReaderId().equals(reader.getReaderId()));
            readers.add(existingReader);
            FileUtil.writeToFile(FilePath.READER_FILE, readers);
            return;
        }

        readers.add(reader);
        FileUtil.writeToFile(FilePath.READER_FILE, readers);
    }

    public void delete(String id) {
        readers.removeIf(reader -> reader.getReaderId().equals(id));
        FileUtil.writeToFile(FilePath.READER_FILE, readers);
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return readers.stream().anyMatch(reader -> reader.getPhoneNumber().equals(phoneNumber));
    }




}
