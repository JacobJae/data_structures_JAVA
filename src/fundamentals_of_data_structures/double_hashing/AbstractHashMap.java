/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fundamentals_of_data_structures.double_hashing;

import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract base class supporting Map implementations that use hash tables
 * with MAD compression.
 *
 * The base class provides the following means of support: 1) Support for
 * calculating hash values with MAD compression 2) Support for resizing table
 * when load factor reaches 1/2
 *
 * Subclass is responsible for providing abstract methods: createTable(),
 * bucketGet(h,k), bucketPut(h,k,v), bucketRemove(h,k), and entrySet() and for
 * accurately maintaining the protected member, n, to reflect changes within
 * bucketPut and bucketRemove.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    protected int n = 0;                 // number of entries in the dictionary
    protected int capacity;              // length of the table
    private int prime;                   // prime factor
    private long scale, shift;           // the shift and scaling factors

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = selectNewCapacity(cap);
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public AbstractHashMap(int cap) {
        this(cap, 109345121);
    }  // default prime

    /**
     * Creates a hash table with capacity 17 and prime factor 109345121.
     */
    public AbstractHashMap() {
        this(17);
    }                     // default capacity

    // public methods
    /**
     * @return number of entries currently stored in map.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns the value associated with the specified key, or null if no such
     * entry exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    /**
     * Removes the entry with the specified key, if present, and returns its
     * associated value. Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     * such entry exists
     */
    @Override
    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    /**
     * Associates the given value with the given key. If an entry with the key
     * was already in the map, this replaced the previous value with the new one
     * and returns the old value. Otherwise, a new entry is added and null is
     * returned.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such
     * entry)
     */
    @Override
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) // keep load factor <= 0.5
        {
            resize(2 * capacity - 1);        // double capacity
        }
        return answer;
    }

    // private utilities
    /**
     * Hash function applying MAD method to default hash code.
     */
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * Updates the size of the hash table to smallest prime greater than 
     * or equal to newCap and rehashes all entries.
     */
    protected void resize(int newCap) {
        Iterable<Entry<K, V>> entries = entrySet();
        capacity = selectNewCapacity(newCap); //Select prime capacity
        createTable();                     // based on updated capacity
        n = 0;                             // will be recomputed while reinserting entries
        for (Entry<K, V> e : entries) {
            put(e.getKey(), e.getValue());
        }
    }

    // protected abstract methods to be implemented by subclasses
    /**
     * Creates an empty table having length equal to current capacity.
     */
    protected abstract void createTable();

    /**
     * Returns value associated with key k in bucket with hash value h. If no
     * such entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    protected abstract V bucketGet(int h, K k);

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketPut(int h, K k, V v);

    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketRemove(int h, K k);

    //Selects new capacity as the smallest prime greater than or equal to minCap
    protected int selectNewCapacity(int minCap) {
        int i = minCap;
        while (!isPrime(i)) {
            i++;
        }
        return i;
    }

    //checks whether an int is prime or not.
    protected boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n % 2 == 0) {
            return false;
        }
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
